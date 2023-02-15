import { Button, Col, Layout, message, Modal, Row, Steps } from 'antd';
import { useEffect, useRef, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from 'react-router-dom';
import AKSCluster from './AKSCluster/AKSCluster';
import { AKSClusterModel } from './AKSCluster/AKSCluster.module';
import Grafana from "./Grafana/Grafana";
import { GrafanaModel } from './Grafana/Grafana.props';
import styles from './MultiStepInitializer.module.less';
import NifiNode from "./NifiNode/NifiNode";
import { NifiNodeModel } from './NifiNode/NifiNode.props';
import NifiRegistry from './NifiRegistry/NifiRegistry';
import { NifiRegistryModel } from './NifiRegistry/NifiRegistry.props';
import Preview from "./Preview/Preview";
import Prometheus from "./Prometheus/Prometheus";
import Zookeeper from "./Zookeeper/Zookeeper";
import { PrometheusModel } from './Prometheus/Prometheus.props';
import Secrets from './Secrets/Secrets';
import { SecretsModel } from './Secrets/Secrets.props';
import { ZookeeperModel } from './Zookeeper/Zookeeper.props';
import { changeTitle } from 'store/title/titleSlice';
import { ExclamationCircleOutlined } from '@ant-design/icons';

const { Step } = Steps;
const { Header, Content, Footer } = Layout;

type StepModels<A, B, C, D, E, F, G> = [A, B, C, D, E, F, G]

const MultiStepInitializer = () => {
  const [current, setCurrent] = useState<number>(0);
  const [steps, setSteps] = useState<any[]>([]);
  const dispatch = useDispatch();
  const [stepsModels, setStepModels] = useState<StepModels<
    AKSClusterModel,
    SecretsModel,
    NifiNodeModel,
    NifiRegistryModel,
    ZookeeperModel,
    GrafanaModel,
    PrometheusModel
  >>([
    {
      clusterName: '',
      namespace: '',
      description: '',
      clusterScope: '',
      namespaceScope: '',
      domainSuffix: '',
      istioGateway: ''
    },
    {
      azureKeyVault: {
        keyvaultName: '',
        userAssignedIdentityId: '',
        tenantId: '',
      },
      azureGitUsername: '',
      azureGitPat: '',
      azDevBasicToken: '',
      certManagerRootCA: '',
      certManagerRootCAPrivateKey: '',
      truststorePassword: '',
      nifiConfigurationEncryptionKey: '',
      sensitivePropertiesEncryptionKey: '',

      certManagerRootCAFileList: [],
      certManagerRootCAPrivateKeyFileList: []
    },
    {
      replicaCount: 0
    },
    {
      enabled: true,
    },
    {
      enabled: true,
    },
    {
      enabled: true,
    },
    {
      enabled: true,
    }
  ]);
  const [currentModel, setCurrentModel] = useState<any>();
  const navigtate = useNavigate();
  const [canNext, setCanNext] = useState<boolean>(false)

  const isValidForm = (isValid: boolean, model: any) => {
    setCanNext(isValid)
    if (model)
      setCurrentModel(model)
  }

  useEffect(() => {
    setSteps([
      {
        title: 'AKS Cluster',
        content: <AKSCluster
          model={stepsModels[0]}
          isValidForm={(isValid, model) => isValidForm(isValid, model)}
        />
      },
      {
        title: 'Secrets',
        content: <Secrets
          model={stepsModels[1]}
          isValidForm={(isValid, model) => isValidForm(isValid, model)}
        />
      },
      {
        title: 'Nifi',
        content: <NifiNode
          model={stepsModels[2]}
          isValidForm={(isValid, model) => isValidForm(isValid, model)}
        />
      },
      {
        title: 'Nifi Registry',
        content: <NifiRegistry
          model={stepsModels[3]}
          isValidForm={(isValid, model) => isValidForm(isValid, model)}
        />
      },
      {
        title: 'Zookeeper',
        content: <Zookeeper
          model={stepsModels[4]}
          isValidForm={(isValid, model) => isValidForm(isValid, model)}
        />
      },
      {
        title: 'Grafana',
        content: <Grafana
          model={stepsModels[5]}
          isValidForm={(isValid, model) => isValidForm(isValid, model)}
        />
      },
      {
        title: 'Prometheus',
        content: <Prometheus
          model={stepsModels[6]}
          isValidForm={(isValid, model) => isValidForm(isValid, model)}
        />
      },
      {
        title: 'Preview',
        content: <Preview
          aksCluster={stepsModels[0]}
          nifiNode={stepsModels[2]}
          nifiRegistry={stepsModels[3]}
          zookeeper={stepsModels[4]}
          grafana={stepsModels[5]}
          prometheus={stepsModels[6]}
        />
      }
    ])
  }, [stepsModels])

  useEffect(() => {
    switch (current) {
      case 0:
        setCurrentModel(stepsModels[0])
        dispatch(changeTitle(`AKS CLuster`))
        break;
      case 1:
        setCurrentModel(stepsModels[1])
        dispatch(changeTitle(`Secrets`))
        break;
      case 2:
        setCurrentModel(stepsModels[2])
        dispatch(changeTitle(`Nifi`))
        break;
      case 3:
        setCurrentModel(stepsModels[3])
        dispatch(changeTitle(`Nifi Registry`))
        break;
      case 4:
        setCurrentModel(stepsModels[4])
        dispatch(changeTitle(`Zookeeper`))
        break;
      case 5:
        setCurrentModel(stepsModels[5])
        dispatch(changeTitle(`Grafana`))
        break;
      case 6:
        setCurrentModel(stepsModels[6])
        dispatch(changeTitle(`Prometheus`))
        break;
      case 7:
        dispatch(changeTitle(`Preview`))
        break;
    }
  }, [current, dispatch])

  const saveStepModel = (stepIndex, model) => {
    let cloneStepModel = stepsModels.map(a => {
      return { ...a }
    })
    cloneStepModel[stepIndex] = model
    setStepModels(cloneStepModel as StepModels<
      AKSClusterModel,
      SecretsModel,
      NifiNodeModel,
      NifiRegistryModel,
      ZookeeperModel,
      GrafanaModel,
      PrometheusModel
    >)
  }

  const next = () => setCurrent(prev => {
    saveStepModel(prev, currentModel);
    return prev + 1;
  });

  const prev = () => setCurrent(prev => {
    saveStepModel(prev, currentModel);
    return prev - 1;
  });

  const cancel = () => {
    Modal.confirm({
      title: 'Warning',
      icon: <ExclamationCircleOutlined />,
      content: 'You have to reimport, do you want to cancel?',
      onOk: () => navigtate(-1)
    })
  }

  const deploy = () => {
    console.log('stepsModels', stepsModels)
  }

  return <> {steps && steps.length > 0 &&
    <Layout style={{ height: "100vh" }}>
      <Header className={styles.header}>nifi-demo-jam / New Flow Deployment</Header>
      <Content className={styles.content}>
        <Row style={{ height: '100%', overflow: 'hidden' }}>
          <Col span={4} style={{ padding: '1em', }}>
            <Steps current={current} direction="vertical">
              {steps.map(item => (
                <Step key={item.title} title={item.title} />
              ))}
            </Steps>
          </Col>
          <Col span={20} className={styles.stepsContent} style={{ height: '100%' }}>
            <div className={styles.stepsContent}>{steps[current].content}</div>
          </Col>
        </Row>
      </Content>
      <Footer className={styles.footer}>
        <Row>
          <Col span={4}></Col>
          <Col span={20}>
            <div className={styles.stepsAction}>
              <div>
                <Button style={{ margin: '0 8px' }} onClick={() => cancel}>Cancel</Button>
              </div>

              <div>
                {current > 0 && (
                  <Button style={{ margin: '0 8px' }} onClick={() => prev()}>
                    Previous
                  </Button>
                )}
                {current < steps.length - 1 && (
                  <Button disabled={!canNext} onClick={() => next()}>
                    Next
                  </Button>
                )}
                {current === steps.length - 1 && (
                  <Button disabled={!canNext} type="primary" onClick={deploy}>
                    Deploy
                  </Button>
                )}
              </div>
            </div>
          </Col>
        </Row>
      </Footer>
    </Layout>}
  </>
};

export default MultiStepInitializer;