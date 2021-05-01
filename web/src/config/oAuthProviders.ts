import {OAuthProvider} from "../types";
import config from "./index";
import GithubIcon from "../assets/icons/github-icon.svg";
import TwitterIcon from "../assets/icons/twitter-icon.svg";

const oAuthProviders: OAuthProvider[] = [
    {url: `${config.baseUrl}/oauth/github/redirect`, icon: GithubIcon},
    {url: `${config.baseUrl}/oauth/twitter/redirect`, icon: TwitterIcon}
];

export default oAuthProviders;