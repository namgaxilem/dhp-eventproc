import { Card, Col, Collapse, Divider, Form, Input, Radio, Row, Select, Space } from 'antd';
import styles from '../MultiStepInitializer.module.less';
import Props from './Grafana.props';

const Grafana = ({}: Props) => {
  return <>
    <Form
      className={styles.formWrapper}
      name="basic"
      labelCol={{ span: 14 }}
      wrapperCol={{ span: 10 }}
      autoComplete="off">
      <Row>
        <Col span={11}>
          <Form.Item label="Enabled" name="radio-group">
            <Radio.Group>
              <Radio value="a">True</Radio>
              <Radio value="b">False</Radio>
            </Radio.Group>
          </Form.Item>

          <Space direction="vertical" className={styles.spaceWidth}>
            <Collapse defaultActiveKey={['1']}>
              <Collapse.Panel header="Image" key="1">
                <Form.Item
                  label="Repository"
                  name="minimumNifiNode" >
                  <Input />
                </Form.Item>
                <Form.Item
                  label="Tag"
                  name="minimumNifiNode" >
                  <Input />
                </Form.Item>
                <Form.Item
                  label="Pull Policy"
                  name="minimumNifiNode" >
                  <Select
                    placeholder="Select a option and change input text above"
                    allowClear>
                    <Select.Option value="male">Always</Select.Option>
                    <Select.Option value="female">female</Select.Option>
                    <Select.Option value="other">other</Select.Option>
                  </Select>
                </Form.Item>
              </Collapse.Panel>
            </Collapse>

            <Collapse defaultActiveKey={['1']}>
              <Collapse.Panel header="Admin" key="1">
                <Form.Item
                  label="Existing Secret"
                  name="minimumNifiNode" >
                  <Input />
                </Form.Item>
              </Collapse.Panel>
            </Collapse>

            <Collapse defaultActiveKey={['1']}>
              <Collapse.Panel header="Datasources" key="1">
                <legend>datasources.yaml</legend>
                <Form.Item
                  label="API Version"
                  name="minimumNifiNode" >
                  <Input type='number' />
                </Form.Item>

                <Form.Item
                  label="Datasources"
                  name="minimumNifiNode" >
                </Form.Item>

                <Card className={styles.cardWrapper}>
                  <Form.Item
                    label="Name"
                    name="minimumNifiNode" >
                    <Input />
                  </Form.Item>
                  <Form.Item
                    label="Type"
                    name="minimumNifiNode" >
                    <Input />
                  </Form.Item>
                  <Form.Item
                    label="URL"
                    name="minimumNifiNode" >
                    <Input />
                  </Form.Item>
                  <Form.Item
                    label="Access"
                    name="minimumNifiNode" >
                    <Input />
                  </Form.Item>
                  <Form.Item label="Is Default" name="radio-group">
                    <Radio.Group>
                      <Radio value="a">True</Radio>
                      <Radio value="b">False</Radio>
                    </Radio.Group>
                  </Form.Item>
                </Card>

              </Collapse.Panel>
            </Collapse>
          </Space>

        </Col>

        <Col span={1} className={styles.divider}>
          <Divider type='vertical' dashed style={{ height: '100%' }} />
        </Col>

        <Col span={11}>
          <Space direction="vertical" className={styles.spaceWidth}>
            <Collapse defaultActiveKey={['1']}>
              <Collapse.Panel header="Dashboard Providers" key="1">
                <legend>dashboardProviders.yaml</legend>
                <Form.Item
                  label="API Version"
                  name="minimumNifiNode" >
                  <Input />
                </Form.Item>
                <Form.Item
                  label="Providers"
                  name="minimumNifiNode" >
                  <Input.TextArea />
                </Form.Item>
              </Collapse.Panel>
            </Collapse>
          </Space>
        </Col>
      </Row>

    </Form>
  </>
}

export default Grafana;