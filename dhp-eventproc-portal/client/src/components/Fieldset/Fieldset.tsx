import { Typography } from 'antd';
import React from 'react';
import styles from './Fieldset.module.less';
import Props from './Fieldset.props'

const { Text } = Typography

const Fieldset = ({ title, children }: Props) => {
  return <div className={styles.fieldSet}>
    <Text className={styles.legend}>{title}</Text>
    {children}
  </div>
}

export default Fieldset;