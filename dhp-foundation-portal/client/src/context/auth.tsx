import { createContext, useContext, useEffect, useState } from "react";
import { getUserInfo } from "services/userInfo";
import User from "types/User";

interface ContextProps {
  user: User | null;
  isLoggedIn: () => any;
}

const AuthContext = createContext({} as ContextProps);

const AuthProvider = (props: any) => {
  const [user, setUser] = useState<User | null>(null);

  useEffect(() => {
    isLoggedIn();
  }, [])

  useEffect(() => {
    localStorage.setItem("user", JSON.stringify(user))
  }, [user])

  const isLoggedIn = async () => {
    try {
      const userInfo = await getUserInfo();
      setUser(userInfo)
    } catch (e) {
      setUser(null)
    }
  };

  return (
    <AuthContext.Provider
      value={{
        user,
        isLoggedIn
      }}
    >
      <>{props.children}</>
    </AuthContext.Provider>
  );
};

const useAuth = () => {
  return useContext(AuthContext);
};

export { AuthProvider, useAuth };

