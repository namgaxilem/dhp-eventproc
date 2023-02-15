interface Props {
    isValidForm: (isValid: boolean, model?: ZookeeperModel) => void
    model: ZookeeperModel
}

export interface ZookeeperModel {
    enabled: boolean
}

export default Props;
