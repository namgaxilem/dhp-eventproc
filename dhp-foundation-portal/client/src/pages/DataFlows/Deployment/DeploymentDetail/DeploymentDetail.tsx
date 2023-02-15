import { Timeline, Typography, message } from "antd";
import { useEffect } from "react";
import { useParams } from "react-router-dom";

const { Text, Title } = Typography;

const DeploymentDetail = () => {
  const params = useParams();
  const [messageApi, contextHolder] = message.useMessage();

  useEffect(() => {
    const eventSource = new EventSource(
      "https://app.tmanet.com/api/deployment/deploymentTimelineSSE",
      { withCredentials: true }
    );
    eventSource.addEventListener("periodic-event", (e) => {
      messageApi.open({
        type: "success",
        content: e.data,
      });
    });
  }, [messageApi]);

  return (
    <>
      {contextHolder}
      <Title>
        Deployment id: <Text>{params.deploymentId}</Text>
      </Title>
      <div>
        <Timeline pending="Recording...">
          <Timeline.Item>Create a services site 2015-09-01</Timeline.Item>
          <Timeline.Item>
            Solve initial network problems 2015-09-01
          </Timeline.Item>
          <Timeline.Item>Technical testing 2015-09-01</Timeline.Item>
        </Timeline>
      </div>
    </>
  );
};

export default DeploymentDetail;
