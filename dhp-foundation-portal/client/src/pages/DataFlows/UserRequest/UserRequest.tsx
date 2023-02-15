import {
    CheckOutlined,
    CloseOutlined,
    FieldTimeOutlined,
    LinkOutlined,
    ReloadOutlined
} from "@ant-design/icons";
import {
    Alert,
    Avatar,
    Button,
    Col,
    Drawer,
    Row,
    Space,
    Table,
    Tabs,
    Tag,
    Typography
} from "antd";
import { ColumnsType } from "antd/lib/table";
import { useAuth } from "context/auth";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { approveRequest } from "services/deployment";
import { openNotificationWithIcon } from "utils/common";

const { Title, Text } = Typography;
const { TabPane } = Tabs;

interface DataType {
    key: string;
    name: string;
    age: number;
    address: string;
    tags: string[];
}

const columns: ColumnsType<DataType> = [
    {
        title: "Name",
        dataIndex: "name",
        key: "name",
        render: (text) => <p>{text}</p>,
    },
    {
        title: "Age",
        dataIndex: "age",
        key: "age",
    },
    {
        title: "Address",
        dataIndex: "address",
        key: "address",
    },
    {
        title: "Tags",
        key: "tags",
        dataIndex: "tags",
        render: (_, { tags }) => (
            <>
                {tags.map((tag) => {
                    let color = tag.length > 5 ? "geekblue" : "green";
                    if (tag === "loser") {
                        color = "volcano";
                    }
                    return (
                        <Tag color={color} key={tag}>
                            {tag.toUpperCase()}
                        </Tag>
                    );
                })}
            </>
        ),
    },
    {
        title: "Action",
        key: "action",
        render: (_, record) => (
            <Space size="middle">
                <p>Invite {record.name}</p>
                <p>Delete</p>
            </Space>
        ),
    },
];

const data: DataType[] = [
    {
        key: "user-request-1",
        name: "Request 1",
        age: 32,
        address: "New York No. 1 Lake Park",
        tags: ["nice", "developer"],
    },
    {
        key: "user-request-2",
        name: "Request 2",
        age: 42,
        address: "London No. 1 Lake Park",
        tags: ["loser"],
    },
    {
        key: "user-request-3",
        name: "Request 3",
        age: 32,
        address: "Sidney No. 1 Lake Park",
        tags: ["cool", "teacher"],
    },
];

const UserRequest = () => {
    const navigate = useNavigate();
    const [isOpenDetail, setIsOpenDetail] = useState(false);
    const { user } = useAuth();
    const onClose = () => setIsOpenDetail(false)
    const requestId = "user-request-id-1"

    return (
        <>
            <Table
                columns={columns}
                dataSource={data}
                onRow={() => {
                    return {
                        onClick: () => {
                            setIsOpenDetail(true);
                        },
                    };
                }}
            />
            <Drawer
                placement="right"
                visible={isOpenDetail}
                onClose={onClose}
                width={750}
                extra={
                    <Space>
                        <Text italic type="secondary">
                            <ReloadOutlined /> &nbsp; Refreshed 3 seconds ago // TODO
                        </Text>
                    </Space>
                }
            >
                <Alert
                    showIcon
                    message="This deployment need admin approval, contact admin for permission"
                    type="warning"
                />
                {user?.roles?.includes("portalAdmin") && (
                    <Space style={{ marginTop: "1em" }}>
                        <Button icon={<CheckOutlined />} type="primary"
                            onClick={async () => {
                                try {
                                    const data = await approveRequest(requestId, { userRequestState: "APPROVED" });
                                    navigate(`/dataflows/deployments/${data.deploymentId}`)
                                } catch (err) {
                                    console.error(err);
                                    openNotificationWithIcon('error', 'err');
                                }
                            }}>
                            Approve
                        </Button>
                        <Button icon={<CloseOutlined />}>Reject</Button>
                    </Space>
                )}
                <Tabs defaultActiveKey="1">
                    <TabPane tab="Infomation" key="1">
                        <Title level={5}>Detail</Title>
                        <Row style={{ margin: "0.75em" }}>
                            <Col span={12}>
                                <Text type="secondary">Deployment Id</Text>
                            </Col>
                            <Col span={12}>
                                <Text strong>deploymentDetail.deploymentId</Text>
                            </Col>
                        </Row>
                        <Row style={{ margin: "0.75em" }}>
                            <Col span={12}>
                                <Text type="secondary">Deployment Name</Text>
                            </Col>
                            <Col span={12}>
                                <Text strong>deploymentDetail.deploymentName</Text>
                            </Col>
                        </Row>
                        <Row style={{ margin: "0.75em" }}>
                            <Col span={12}>
                                <Text type="secondary">Deployed At</Text>
                            </Col>
                            <Col span={12}>
                                <Text strong>deploymentDetail.deployedAt</Text>
                            </Col>
                        </Row>
                        <Row style={{ margin: "0.75em" }}>
                            <Col span={12}>
                                <Text type="secondary">Deployed By</Text>
                            </Col>
                            <Col span={12}>
                                <Space>
                                    <Avatar size="small">U</Avatar>
                                    <Text strong>deploymentDetail.deployedBy</Text>
                                </Space>
                            </Col>
                        </Row>
                        <Row style={{ margin: "0.75em" }}>
                            <Col span={12}>
                                <Text type="secondary">State</Text>
                            </Col>
                            <Col span={12}>
                                <Tag icon={<FieldTimeOutlined />} color="purple">
                                    deploymentDetail.state
                                </Tag>
                            </Col>
                        </Row>
                        <Title level={5}>
                            Flow detail{" "}
                            <a>
                                <LinkOutlined />
                            </a>
                        </Title>
                        <Row style={{ margin: "0.75em" }}>
                            <Col span={12}>
                                <Text type="secondary">Flow Id</Text>
                            </Col>
                            <Col span={12}>
                                <Text strong>deploymentDetail.flowId</Text>
                            </Col>
                        </Row>
                        <Row style={{ margin: "0.75em" }}>
                            <Col span={12}>
                                <Text type="secondary">Flow version</Text>
                            </Col>
                            <Col span={12}>
                                <Text strong>deploymentDetail.version</Text>
                            </Col>
                        </Row>
                        <Title level={5}>
                            Cluster detail{" "}
                            <a>
                                <LinkOutlined />
                            </a>
                        </Title>
                        <Row style={{ margin: "0.75em" }}>
                            <Col span={12}>
                                <Text type="secondary">Cluster id</Text>
                            </Col>
                            <Col span={12}>
                                <Text strong>deploymentDetail.environmentId</Text>
                            </Col>
                        </Row>
                    </TabPane>
                    <TabPane tab="Parameters" key="2"></TabPane>
                </Tabs>
            </Drawer>
        </>
    );
};

export default UserRequest;
