import {OAuthProvider} from "../types";
import config from "./index";
import GithubIcon from "../assets/icons/github-icon.svg";
import TwitterIcon from "../assets/icons/twitter-icon.svg";

const oAuthProviders: OAuthProvider[] = [
    {url: `${config.baseUrl}/auth/login/oauth/github/start`, icon: GithubIcon},
    {url: `${config.baseUrl}/auth/login/oauth/twitter/start`, icon: TwitterIcon}
];

export default oAuthProviders;