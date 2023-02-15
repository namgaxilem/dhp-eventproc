import { Col, Form, Input, Row } from "antd";
import { useForm } from "antd/es/form/Form";
import { Rule } from "antd/lib/form";
import { useEffect } from "react";
import Props from "./AKSCluster.module";


const ruleRequiredOnly: Rule[] = [
  { required: true, message: "" }
];

const ruleNamingConvention: Rule[] = [
  { pattern: new RegExp("^[_a-zA-Z]+[a-zA-Z0-9_-]+[a-zA-Z0-9_]$"), message: "Name must contain atleast 3 letter" }
];

const AKSCluster = ({ isValidForm, model }: Props) => {
  const [form] = useForm();

  useEffect(() => {
    onValuesChange()
  }, [form])

  const onValuesChange = () => {
    Promise.resolve().then(() => {
      form.submit();
    })
  }

  const onFinish = isValid => {
    isValidForm(isValid, {
      clusterName: form.getFieldValue('clusterName'),
      namespace: form.getFieldValue('namespace'),
      description: form.getFieldValue('description'),
      clusterScope: form.getFieldValue('clusterScope'),
      namespaceScope: form.getFieldValue('namespaceScope'),
      domainSuffix: form.getFieldValue('domainSuffix'),
      istioGateway: form.getFieldValue('istioGateway'),
    })
  }

  return (
    <>
      <Form
        name="AKSCluster"
        form={form}
        onValuesChange={onValuesChange}
        onFinish={() => onFinish(true)}
        onFinishFailed={() => onFinish(false)}

      >
        <Row>
          <Col span={6}>
            <Form.Item label="clusterName"></Form.Item>
          </Col>
          <Col span={18}>
            <Form.Item
              name="clusterName"
              initialValue={model.clusterName}
              rules={[
                ...ruleRequiredOnly,
                ...ruleNamingConvention
              ]}
            >
              <Input />
            </Form.Item>
          </Col>
        </Row>

        <Row>
          <Col span={6}>
            <Form.Item label="namespace"></Form.Item>
          </Col>
          <Col span={18}>
            <Form.Item
              name="namespace"
              initialValue={model.namespace}
              rules={[
                ...ruleRequiredOnly,
              ]}
            >
              <Input />
            </Form.Item>
          </Col>
        </Row>

        <Row>
          <Col span={6}>
            <Form.Item label="description"></Form.Item>
          </Col>
          <Col span={18}>
            <Form.Item
              name="description"
              initialValue={model.description}
              required={false}
            >
              <Input />
            </Form.Item>
          </Col>
        </Row>

        <Row>
          <Col span={6}>
            <Form.Item label="clusterScope"></Form.Item>
          </Col>
          <Col span={18}>
            <Form.Item
              name="clusterScope"
              initialValue={model.clusterScope}
              rules={[
                ...ruleRequiredOnly,
              ]}
            >
              <Input />
            </Form.Item>
          </Col>
        </Row>

        <Row>
          <Col span={6}>
            <Form.Item label="namespaceScope"></Form.Item>
          </Col>
          <Col span={18}>
            <Form.Item
              name="namespaceScope"
              initialValue={model.namespaceScope}
              rules={[
                ...ruleRequiredOnly,
              ]}
            >
              <Input />
            </Form.Item>
          </Col>
        </Row>

        <Row>
          <Col span={6}>
            <Form.Item label="domainSuffix"></Form.Item>
          </Col>
          <Col span={18}>
            <Form.Item
              name="domainSuffix"
              initialValue={model.domainSuffix}
              rules={[
                ...ruleRequiredOnly,
              ]}
            >
              <Input />
            </Form.Item>
          </Col>
        </Row>

        <Row>
          <Col span={6}>
            <Form.Item label="istioGateway"></Form.Item>
          </Col>
          <Col span={18}>
            <Form.Item
              name="istioGateway"
              initialValue={model.istioGateway}
              rules={[
                ...ruleRequiredOnly,
              ]}
            >
              <Input />
            </Form.Item>
          </Col>
        </Row>
      </Form>
    </>
  );
};

export default AKSCluster;
