import { Button, Space } from "antd";
import CatalogDetail from "components/CatalogDetail/CatalogDetail";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { getFlows } from "services/catalog";
import { changeTitle } from "store/title/titleSlice";

const Catalog = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();

  useEffect(() => {
    dispatch(changeTitle("Flow Catalog"));
  });

  const getCatalog = async () => {
    try {
      const data = await getFlows(1, 1);
      console.log(data);
    } catch (err) {
      console.error(err);
    }
  };

  return (
    <Space direction={"vertical"}>
      <CatalogDetail />
      <Button onClick={() => navigate("/catalog/123-456-uuid")}>Go to catalog detail</Button>
      <Button onClick={getCatalog}>get Catalog Test</Button>
    </Space>
  );
};

export default Catalog;
