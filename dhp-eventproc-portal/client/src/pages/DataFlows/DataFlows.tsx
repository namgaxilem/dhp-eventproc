import { Tabs } from "antd";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Deployment from "./Deployment/Deployment";
import UserRequest from "./UserRequest/UserRequest";

const { TabPane } = Tabs;

const DataFlows = () => {
  const navigate = useNavigate();
  const [tabKey, setTabKey] = useState<string>("1");
  const location = useLocation();

  useEffect(() => {
    if (location.pathname.includes('deployments')) {
      setTabKey('2');
    } else {
      setTabKey('1')
    }
  }, [location])

  const onNavigateTab = (tabkey) =>
    tabkey === '1' ? navigate('/dataflows/user-requests') : navigate('/dataflows/deployments')

  return (
    <>
      <Tabs
        defaultActiveKey={tabKey}
        activeKey={tabKey}
        onChange={(tabKey) => onNavigateTab(tabKey)}
      >
        <TabPane tab='Requests' key={1}>
          {tabKey === "1" && <UserRequest />}
        </TabPane>
        <TabPane tab='Deployment' key={2}>
          {tabKey === "2" && <Deployment />}
        </TabPane>
      </Tabs>
    </>
  );
};

export default DataFlows;
