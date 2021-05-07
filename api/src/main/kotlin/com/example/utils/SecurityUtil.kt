package com.example.utils

import io.ktor.http.*
import io.ktor.http.auth.*
import io.ktor.util.*
import org.mindrot.jbcrypt.BCrypt
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

fun hashPassword(clearText: String): String {
    return BCrypt.hashpw(clearText, BCrypt.gensalt())
}

fun arePasswordsEqual(plaintextPassword: String, hashedPassword: String): Boolean {
    return BCrypt.checkpw(plaintextPassword, hashedPassword)
}

fun getSignedOAuth1aAuthorizationHeader(
    consumerKey: String,
    consumerSecret: String,
    token: String,
    tokenSecret: String,
    method: HttpMethod,
    baseUrl: String,
    extraParams: List<Pair<String, String>> = emptyList()
): String =
    generateOAuth1aAuthorizationHeader(consumerKey, token)
        .sign(
            method,
            baseUrl,
            "${consumerSecret.encodeURLParameter()}&${tokenSecret.encodeURLParameter()}",
            extraParams
        ).render(HeaderValueEncoding.URI_ENCODE)

internal fun generateOAuth1aAuthorizationHeader(
    consumerKey: String,
    token: String,
    nonce: String = generateNonce(),
    timestamp: LocalDateTime = LocalDateTime.now()
): HttpAuthHeader.Parameterized = HttpAuthHeader.Parameterized(
    authScheme = AuthScheme.OAuth,
    parameters = mapOf(
        HttpAuthHeader.Parameters.OAuthConsumerKey to consumerKey,
        HttpAuthHeader.Parameters.OAuthToken to token,
        HttpAuthHeader.Parameters.OAuthNonce to nonce,
        HttpAuthHeader.Parameters.OAuthSignatureMethod to "HMAC-SHA1",
        HttpAuthHeader.Parameters.OAuthTimestamp to timestamp.toEpochSecond(ZoneOffset.UTC).toString(),
        HttpAuthHeader.Parameters.OAuthVersion to "1.0"
    )
)

private fun HttpAuthHeader.Parameterized.sign(
    method: HttpMethod,
    baseUrl: String,
    key: String,
    parameters: List<Pair<String, String>>
): HttpAuthHeader.Parameterized = withParameter(
    HttpAuthHeader.Parameters.OAuthSignature,
    getSignatureBaseString(this, method, baseUrl, parameters.toHeaderParamsList()).hmacSha1(key)
)

internal fun getSignatureBaseString(
    header: HttpAuthHeader.Parameterized,
    method: HttpMethod,
    baseUrl: String,
    parameters: List<HeaderValueParam>
): String = listOf(
    method.value.toUpperCase(),
    baseUrl,
    getParametersString(header.parameters + parameters)
).joinToString("&") { it.encodeURLParameter() }

internal fun getParametersString(parameters: List<HeaderValueParam>): String =
    parameters.map { it.name.encodeURLParameter() to it.value.encodeURLParameter() }
        .sortedWith(compareBy<Pair<String, String>> { it.first }.then(compareBy { it.second }))
        .joinToString("&") { "${it.first}=${it.second}" }

internal fun String.hmacSha1(key: String): String {
    val keySpec = SecretKeySpec(key.toByteArray(), "HmacSHA1")
    val mac = Mac.getInstance("HmacSHA1")
    mac.init(keySpec)

    return Base64.getEncoder().encodeToString(mac.doFinal(this.toByteArray()))
}