import { Col, Collapse, Divider, Form, Input, Radio, Row, Select, Space } from 'antd';
import styles from '../MultiStepInitializer.module.less';
import Props from './Prometheus.props';

const Prometheus = ({ }: Props) => {
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
              <Collapse.Panel header="Server" key="1">
                <Form.Item
                  label="Replica Count"
                  name="minimumNifiNode" >
                  <Input type='number' />
                </Form.Item>
                <Form.Item
                  label="Service Port"
                  name="minimumNifiNode" >
                  <Input type='number' />
                </Form.Item>

                <legend>Image</legend>
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
                    <Select.Option value="female">IfNotPresent</Select.Option>
                    <Select.Option value="other">other</Select.Option>
                  </Select>
                </Form.Item>

                <legend>Statefulset</legend>
                <Form.Item label="Enabled" name="radio-group">
                  <Radio.Group>
                    <Radio value="a">True</Radio>
                    <Radio value="b">False</Radio>
                  </Radio.Group>
                </Form.Item>
                <Form.Item
                  label="Pod Management Policy"
                  name="minimumNifiNode" >
                  <Select
                    placeholder="Select a option and change input text above"
                    allowClear>
                    <Select.Option value="male">OrderedReady</Select.Option>
                    <Select.Option value="female">IfNotPresent</Select.Option>
                  </Select>
                </Form.Item>
                <legend>Headless</legend>
                <Form.Item
                  label="Service Port"
                  name="minimumNifiNode" >
                  <Input type='number' />
                </Form.Item>
                <Form.Item label="gRPC Enabled" name="radio-group">
                  <Radio.Group>
                    <Radio value="a">True</Radio>
                    <Radio value="b">False</Radio>
                  </Radio.Group>
                </Form.Item>

                <Form.Item label="Extra Args" name="radio-group">
                  <Input.TextArea />
                </Form.Item>
              </Collapse.Panel>
            </Collapse>

            <Collapse defaultActiveKey={['1']}>
              <Collapse.Panel header="RBAC" key="1">
                <Form.Item label="Create" name="radio-group">
                  <Radio.Group>
                    <Radio value="a">True</Radio>
                    <Radio value="b">False</Radio>
                  </Radio.Group>
                </Form.Item>
              </Collapse.Panel>
            </Collapse>

            <Collapse defaultActiveKey={['1']}>
              <Collapse.Panel header="Alert Manager" key="1">
                <Form.Item label="Enabled" name="radio-group">
                  <Radio.Group>
                    <Radio value="a">True</Radio>
                    <Radio value="b">False</Radio>
                  </Radio.Group>
                </Form.Item>
              </Collapse.Panel>
            </Collapse>

            <Collapse defaultActiveKey={['1']}>
              <Collapse.Panel header="Kube State Metrics" key="1">
                <Form.Item label="Enabled" name="radio-group">
                  <Radio.Group>
                    <Radio value="a">True</Radio>
                    <Radio value="b">False</Radio>
                  </Radio.Group>
                </Form.Item>

                <legend>Image</legend>
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
                    <Select.Option value="female">IfNotPresent</Select.Option>
                    <Select.Option value="other">other</Select.Option>
                  </Select>
                </Form.Item>
              </Collapse.Panel>
            </Collapse>

            <Collapse defaultActiveKey={['1']}>
              <Collapse.Panel header="Configmap Reload" key="1">
                <Form.Item label="Enabled" name="radio-group">
                  <Radio.Group>
                    <Radio value="a">True</Radio>
                    <Radio value="b">False</Radio>
                  </Radio.Group>
                </Form.Item>
              </Collapse.Panel>
            </Collapse>

            <Collapse defaultActiveKey={['1']}>
              <Collapse.Panel header="Node Exporter" key="1">
                <Form.Item label="Enabled" name="radio-group">
                  <Radio.Group>
                    <Radio value="a">True</Radio>
                    <Radio value="b">False</Radio>
                  </Radio.Group>
                </Form.Item>
              </Collapse.Panel>
            </Collapse>

            <Collapse defaultActiveKey={['1']}>
              <Collapse.Panel header="Push Gateway" key="1">
                <Form.Item label="Enabled" name="radio-group">
                  <Radio.Group>
                    <Radio value="a">True</Radio>
                    <Radio value="b">False</Radio>
                  </Radio.Group>
                </Form.Item>
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
              <Collapse.Panel header="Server Files" key="1">
                <Form.Item
                  label="alerting_rules.yaml"
                  name="minimumNifiNode" >
                  <Input.TextArea />
                </Form.Item>
                <Form.Item
                  label="alerts"
                  name="minimumNifiNode" >
                  <Input.TextArea />
                </Form.Item>
                <Form.Item
                  label="recording_rules.yaml"
                  name="minimumNifiNode" >
                  <Input.TextArea />
                </Form.Item>
                <Form.Item
                  label="rules"
                  name="minimumNifiNode" >
                  <Input.TextArea />
                </Form.Item>
                <Form.Item
                  label="prometheus.yaml"
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

export default Prometheus;