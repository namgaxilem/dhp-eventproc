import { Button, Drawer, Space } from 'antd';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const CatalogDetail: React.FC = () => {
  const [visible, setVisible] = useState(false);
  const navigate = useNavigate();

  const showDrawer = () => {
    setVisible(true);
  };

  const onClose = () => {
    setVisible(false);
  };

  return (
    <>
      <Space>
        <Button type="primary" onClick={showDrawer}>
          Open Default Size (378px)
        </Button>
      </Space>
      <Drawer
        title={`Drawer`}
        placement="right"
        onClose={onClose}
        visible={visible}
        extra={
          <Space>
            <Button onClick={onClose}>Cancel</Button>
            <Button type="primary" onClick={() => navigate('/catalog/deploy-flow')}>
              OK
            </Button>
          </Space>
        }
      >
        <p>Some contents...</p>
        <p>Some contents...</p>
        <p>Some contents...</p>
      </Drawer>
    </>
  );
};

export default CatalogDetail;