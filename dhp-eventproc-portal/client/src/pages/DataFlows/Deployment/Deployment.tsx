import { Space, Table, Tag } from "antd";
import { ColumnsType } from "antd/lib/table";
import { useNavigate } from "react-router-dom";

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
        key: "1",
        name: "Deployment 1",
        age: 32,
        address: "New York No. 1 Lake Park",
        tags: ["nice", "developer"],
    },
    {
        key: "2",
        name: "Deployment 2",
        age: 42,
        address: "London No. 1 Lake Park",
        tags: ["loser"],
    },
    {
        key: "3",
        name: "Deployment 3",
        age: 32,
        address: "Sidney No. 1 Lake Park",
        tags: ["cool", "teacher"],
    },
];

const Deployment = () => {
    const navigate = useNavigate();
    return (
        <Table
            columns={columns}
            dataSource={data}
            onRow={() => {
                return {
                    onClick: () => {
                        navigate(`/dataflows/deployments/deployment-id-1`);
                    },
                };
            }}
        />

    );
};

export default Deployment;
