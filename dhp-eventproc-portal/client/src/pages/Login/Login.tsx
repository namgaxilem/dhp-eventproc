import { KeyOutlined, LockOutlined, UserOutlined } from "@ant-design/icons";
import {
  Button,
  Card,
  Checkbox,
  Divider,
  Form,
  Input,
  Layout,
  notification,
  Typography,
} from "antd";
import MS_LOGO from "assets/ms_logo.png";
import { LOGIN_ENDPOINT } from "config/constants";
import { useAuth } from "context/auth";
import { postDeployment } from "services/deployment";
import styles from "./Login.module.less";

const { Text } = Typography;

const Login = () => {
  const [form] = Form.useForm();

  const onFinish = () => {
    const args = {
      message: "Notification",
      description: "Currently only support AAD authentication",
    };
    notification.open(args);
  };

  return (
    <Layout className={styles.layout}>
      <Card
        className={styles.loginForm}
        title={
          <div style={{ textAlign: "center" }}>
            <Text strong className={styles.logoText}>
              DHP EventProc
            </Text>
          </div>
        }
      >
        <Button
          type="dashed"
          block
          className={styles.OptionalLoginButton}
          onClick={() => (window.location.href = LOGIN_ENDPOINT)}
        >
          <img src={MS_LOGO} alt="MS Logo" className={styles.ms_logo} />
          <Text style={{ width: "100%" }}>Login with AAD</Text>
        </Button>
      </Card>
    </Layout>
  );
};

export default Login;
