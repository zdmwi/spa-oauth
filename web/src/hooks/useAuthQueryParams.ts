import {useEffect} from "react";
import useQueryParams from "./useQueryParams";
import {useHistory} from "react-router-dom";

const REFRESH_TOKEN_KEY = "refreshToken";
const ACCESS_TOKEN_KEY = "accessToken";

export default function useAuthQueryParams() {
  const history = useHistory();
  const query = useQueryParams();

  useEffect(() => {
    if (query.get(ACCESS_TOKEN_KEY) !== null && query.get(REFRESH_TOKEN_KEY) !== null) {
      const accessToken = query.get(ACCESS_TOKEN_KEY) as string
      const refreshToken = query.get(REFRESH_TOKEN_KEY) as string

      // save the data to your application's store,
      // I'm using localStorage because I'm lazy :p
      localStorage.setItem(ACCESS_TOKEN_KEY, accessToken);
      localStorage.setItem(REFRESH_TOKEN_KEY, refreshToken);

      console.log(
        localStorage.getItem(ACCESS_TOKEN_KEY),
        localStorage.getItem(REFRESH_TOKEN_KEY)
      );
      history.push("/home");
    }
  }, [query, history])
}