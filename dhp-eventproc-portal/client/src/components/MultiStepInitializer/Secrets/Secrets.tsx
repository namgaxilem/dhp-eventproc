import { InboxOutlined } from "@ant-design/icons";
import { Form, Upload } from "antd";
import { Rule } from "antd/lib/form";
import { useEffect, useState } from "react";
import Props from "./Secrets.props";

const ruleFileRequired: Rule[] = [
  { required: true, message: "Select a file" }
];

const Secrets = ({ isValidForm, model }: Props) => {
  const [form] = Form.useForm();
  const [certManagerRootCAFileList, setCertManagerRootCAFileList] = useState<any[]>([...model.certManagerRootCAFileList])
  const [certManagerRootCAPrivateKeyFileList, setCertManagerRootCAPrivateKeyFileList] = useState<any[]>([...model.certManagerRootCAPrivateKeyFileList])

  useEffect(() => {
    onValuesChange()
  }, [form, certManagerRootCAFileList, certManagerRootCAPrivateKeyFileList])

  const onValuesChange = () => {
    Promise.resolve().then(() => {
      form.submit();
    })
  }

  const normFile = (e: any) => {
    if (Array.isArray(e)) return e;
    return e && e.fileList;
  };

  const onUpload = (file, fieldName) => {
    const reader = new FileReader();
    reader.addEventListener("load", (event) => {
      const result: string = event?.target?.result as string;
      form.setFieldsValue({
        [fieldName]: result.substring(result.indexOf(',') + 1) as string,
      })
      const temp = [{
        name: file.name,
        uid: file.uid,
        status: 'success'
      }]
      if (fieldName === 'certManagerRootCA') setCertManagerRootCAFileList(temp)
      if (fieldName === 'certManagerRootCAPrivateKey') setCertManagerRootCAPrivateKeyFileList(temp)
    });
    reader.readAsDataURL(file);
    return Promise.resolve(file);
  };

  const onFinish = (isValid) => {
    const azDevBasicToken = window.btoa(`${form.getFieldValue('azureGitUsername')}:${form.getFieldValue('azureGitPat')}`)
    isValidForm(isValid, {
      azureKeyVault: {
        keyvaultName: form.getFieldValue('keyvaultName'),
        userAssignedIdentityId: form.getFieldValue('userAssignedIdentityId'),
        tenantId: form.getFieldValue('tenantId'),
      },
      azureGitUsername: form.getFieldValue('azureGitUsername'),
      azureGitPat: form.getFieldValue('azureGitPat'),
      azDevBasicToken: azDevBasicToken,
      certManagerRootCA: form.getFieldValue('certManagerRootCA'),
      certManagerRootCAPrivateKey: form.getFieldValue('certManagerRootCAPrivateKey'),
      truststorePassword: form.getFieldValue('truststorePassword'),
      nifiConfigurationEncryptionKey: form.getFieldValue('nifiConfigurationEncryptionKey'),
      sensitivePropertiesEncryptionKey: form.getFieldValue('sensitivePropertiesEncryptionKey'),

      certManagerRootCAFileList: certManagerRootCAFileList,
      certManagerRootCAPrivateKeyFileList: certManagerRootCAPrivateKeyFileList,
    })
  };

  const onFileRemove = (fieldName) => {
    form.setFieldsValue({ [fieldName]: '' })

    if (fieldName === 'certManagerRootCA') {
      setCertManagerRootCAFileList([])
    } else if (fieldName === 'certManagerRootCAPrivateKey') {
      setCertManagerRootCAPrivateKeyFileList([])
    }

    form.submit()
  };

  return (
    <>
      <Form
        name="Secrets"
        form={form}
        onValuesChange={onValuesChange}
        onFinish={() => onFinish(true)}
        onFinishFailed={() => onFinish(false)}
      >
        <Form.Item
          label="certManagerRootCA"
          name="certManagerRootCA"
          initialValue={model.certManagerRootCA}
          rules={[...ruleFileRequired]}
        >
          <Form.Item
            valuePropName="fileList"
            getValueFromEvent={normFile}
          >
            <Upload.Dragger
              maxCount={1}
              name="files"
              defaultFileList={[...model.certManagerRootCAFileList]}
              onRemove={() => onFileRemove('certManagerRootCA')}
              onChange={info => onUpload(info.file, 'certManagerRootCA')}
              beforeUpload={() => false}
            >
              <p className="ant-upload-drag-icon">
                <InboxOutlined />
              </p>
            </Upload.Dragger>
          </Form.Item>
        </Form.Item>

        <Form.Item
          label="certManagerRootCAPrivateKey"
          name="certManagerRootCAPrivateKey"
          initialValue={model.certManagerRootCAPrivateKey}
          rules={[...ruleFileRequired]}
        >
          <Form.Item
            valuePropName="fileList"
            getValueFromEvent={normFile}
          >
            <Upload.Dragger
              maxCount={1}
              name="files1"
              defaultFileList={[...model.certManagerRootCAPrivateKeyFileList]}
              onRemove={() => onFileRemove('certManagerRootCAPrivateKey')}
              onChange={info => onUpload(info.file, 'certManagerRootCAPrivateKey')}
              beforeUpload={() => false}
            >
              <p className="ant-upload-drag-icon">
                <InboxOutlined />
              </p>
            </Upload.Dragger>
          </Form.Item>
        </Form.Item>

      </Form>

    </>
  );
}

export default Secrets;
