import { Col, Divider, Row, Typography } from 'antd';
import styles from './Preview.module.less';
import Props from './Preview.props';

const { Text } = Typography;

const Preview = ({ }: Props) => {
  return <>
    <h3>Cluster</h3>
    <Row>
      <Col span={12} className={styles.contenPadding}>
        <div>
          <Text type="secondary">Nifi Cluster Namespace: </Text>
          <Text strong>dhp-eventproc-dev</Text>
        </div>
        <div>
          <Text type="secondary">Kubernetes Service Connections</Text>
        </div>
        <div className={styles.contenPadding}>
          <div>
            <Text type="secondary">Cluster Scope: </Text>
            <Text strong>dhp-pisp-aks-npe</Text>
          </div>
          <div>
            <Text type="secondary">Namespace Scope: </Text>
            <Text strong>dhp-pisp-aks-npe-dev</Text>
          </div>
        </div>
      </Col>
      <Col span={12}>
        <Text type="secondary">Namespace: </Text>
        <Text strong>nifi-dev</Text>
      </Col>
    </Row>
    <Divider dashed />

    <h3>Node</h3>
    <Row>
      <Col span={12} className={styles.contenPadding}>
        <div>
          <Text type="secondary">Number Of Nifi Node: </Text>
          <Text strong>3</Text>
        </div>
      </Col>
      <Col span={12}>
        <Text type="secondary">Autoscaling Nifi Node: </Text>
        <Text strong>Enalbed</Text>
      </Col>
    </Row>
    <Divider dashed />

    <h3>Nifi Registry</h3>
    <Row>
      <Col span={12} className={styles.contenPadding}>
        <div>
          <Text type="secondary">Enabled: </Text>
          <Text strong>True</Text>
        </div>
      </Col>
    </Row>
    <Divider dashed />

    <h3>Zookeeper</h3>
    <Row>
      <Col span={12} className={styles.contenPadding}>
        <div>
          <Text type="secondary">Enabled: </Text>
          <Text strong>True</Text>
        </div>
      </Col>
    </Row>
    <Divider dashed />

    <h3>Grafana</h3>
    <Row>
      <Col span={12} className={styles.contenPadding}>
        <div>
          <Text type="secondary">Enabled: </Text>
          <Text strong>True</Text>
        </div>
      </Col>
    </Row>
    <Divider dashed />

    <h3>Prometheus</h3>
    <Row>
      <Col span={12} className={styles.contenPadding}>
        <div>
          <Text type="secondary">Enabled: </Text>
          <Text strong>True</Text>
        </div>
      </Col>
    </Row>
  </>
}

export default Preview;