import {
  CloudOutlined,
  DatabaseOutlined,
  DeploymentUnitOutlined,
  DoubleLeftOutlined,
  LogoutOutlined,
  QuestionCircleOutlined,
  UserOutlined,
} from "@ant-design/icons";
import {
  Avatar,
  Breadcrumb,
  Dropdown,
  Layout,
  Menu,
  Space,
  Typography,
} from "antd";
import DeploymentDetail from "pages/DataFlows/Deployment/DeploymentDetail/DeploymentDetail";
import { LOGOUT_ENDPOINT } from "config/constants";
import { useAuth } from "context/auth";
import DataFlows from "pages/DataFlows/DataFlows";
import EnvironmentDetail from "pages/Environments/EnvironmentDetail/EnvironmentDetail";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import {
  Link,
  Navigate,
  NavLink,
  Route,
  Routes,
  useLocation,
} from "react-router-dom";
import { RootStoreState } from "store/store";
import { getUserFirstAndLastNameCharacter } from "utils/common";
import Catalog from "../Catalog/Catalog";
import Environments from "../Environments/Environments";
import styles from "./RouteWithSidenav.module.scss";
import { modalGlobalConfig } from "antd/lib/modal/confirm";

const { Header, Content, Footer, Sider } = Layout;
const { Text } = Typography;

const RouteWithSidenav = () => {
  const [collapsed, setCollapsed] = useState(false);
  const [selectedKeys, setSelectedKeys] = useState<string[]>(["1"]);
  const location = useLocation();
  const [appVersion, setAppVersion] = useState("app.version");
  const pathSnippets = location.pathname.split("/").filter((i) => i);
  const breadcrumbNameMap = useSelector(
    (state: RootStoreState) => state.breadcrumbNameMap.value
  );
  const { user } = useAuth();

  const extraBreadcrumbItems = pathSnippets.map((_, index) => {
    const url = `/${pathSnippets.slice(0, index + 1).join("/")}`;
    return (
      <Breadcrumb.Item key={url}>
        <Link to={url}>{breadcrumbNameMap[url]}</Link>
      </Breadcrumb.Item>
    );
  });

  const handleMenuClick = (e) => {
    if (e.key === "1") {
      alert(user ? (user.name): 'Unauthorized');
    }
    if (e.key === "2") {
      window.location.href = LOGOUT_ENDPOINT;
    }
  };

  const itemMenus = [
    {
      label: (
        <Text>
          <UserOutlined /> User Info
        </Text>
      ),
      key: "1",
    },
    {
      label: (
        <Text>
          <LogoutOutlined /> Log Out
        </Text>
      ),
      key: "2",
    },
  ];

  const sideMenuItems = [
    {
      label: <NavLink to="/catalog">Catalog</NavLink>,
      icon: <DatabaseOutlined />,
      key: "1",
    },
    {
      label: <NavLink to="/clusters">Clusters</NavLink>,
      icon: <CloudOutlined />,
      key: "2",
    },
    {
      label: <NavLink to="/dataflows">Data Flows</NavLink>,
      icon: <DeploymentUnitOutlined />,
      key: "3",
    },
  ];

  const sideMenuBottomItems = [
    {
      labal: <NavLink to="/help">Help</NavLink>,
      icon: <QuestionCircleOutlined />,
      disabled: true,
      key: 1,
    },
  ];

  const menu = <Menu items={itemMenus} onClick={handleMenuClick} />;

  useEffect(() => {
    if (location.pathname.includes("clusters")) setSelectedKeys(["2"]);
    else if (location.pathname.includes("dataflows")) setSelectedKeys(["3"]);
    else setSelectedKeys(["1"]);
  }, [location]);

  return (
    <Layout style={{ minHeight: "100vh", overflow: "hidden" }}>
      <Sider
        theme={"light"}
        collapsed={collapsed}
        onCollapse={() => setCollapsed(!collapsed)}
      >
        <div className={styles.logo} />
        <Menu
          theme="light"
          selectedKeys={selectedKeys}
          mode="inline"
          items={sideMenuItems}
        />
        <Menu
          mode="inline"
          style={{ position: "absolute", bottom: 48, width: "100%" }}
          items={sideMenuBottomItems}
        />

        <div className={styles.collapseButton}>
          <span>{appVersion}</span>
          <DoubleLeftOutlined />
        </div>
      </Sider>

      <Layout className="site-layout">
        <Header className={styles.siteLayoutBackground} style={{ padding: 0 }}>
          <Breadcrumb className={styles.breadcumb}>
            {extraBreadcrumbItems}
          </Breadcrumb>

          <Dropdown overlay={menu} placement="bottom" trigger={["click"]}>
            <div className={styles.avatar}>
              <div
                style={{
                  display: "flex",
                  flexDirection: "column",
                  height: "100%",
                  padding: "1em",
                }}
              >
                <Space
                  className="mock-block"
                  style={{
                    height: "50%",
                    lineHeight: "100%",
                    justifyContent: "end",
                  }}
                >
                  <Typography.Text strong>{user && user.name}</Typography.Text>
                </Space>
                <Space
                  className="mock-block"
                  style={{
                    height: "50%",
                    lineHeight: "100%",
                    justifyContent: "end",
                  }}
                >
                  <Typography.Text type="secondary">
                    Portal Admin
                  </Typography.Text>
                </Space>
              </div>
              <Avatar
                size={"large"}
                style={{ color: "#f56a00", backgroundColor: "#fde3cf" }}
              >
                {getUserFirstAndLastNameCharacter(user?.name || "")}
              </Avatar>
            </div>
          </Dropdown>
        </Header>
        <Content className={styles.siteLayoutContent}>
          <Routes>
            <Route path="/catalog/*">
              <Route index={true} element={<Catalog />}></Route>
            </Route>
            <Route path="/clusters">
              <Route index={true} element={<Environments />}></Route>
              <Route path=":clusterId" element={<EnvironmentDetail />}></Route>
            </Route>
            <Route path="/dataflows/*">
              <Route index={true} element={<DataFlows />}></Route>
              <Route path="user-requests" element={<DataFlows />}></Route>
              <Route path="deployments" element={<DataFlows />}></Route>
              <Route
                path="user-requests/:requestId"
                element={<DataFlows />}
              ></Route>
              <Route
                path="deployments/:deploymentId"
                element={<DeploymentDetail />}
              ></Route>
            </Route>
            <Route
              path="/*"
              element={<Navigate to={{ pathname: "/catalog" }} />}
            ></Route>
          </Routes>
        </Content>
        <Footer style={{ textAlign: "center" }}>
          Ant Design ©2018 Created by Ant UED
        </Footer>
      </Layout>
    </Layout>
  );
};

export default RouteWithSidenav;
