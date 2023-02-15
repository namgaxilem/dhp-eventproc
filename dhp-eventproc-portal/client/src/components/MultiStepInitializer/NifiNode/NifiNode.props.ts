interface Props {
    isValidForm: (isValid: boolean, model?: NifiNodeModel) => void
    model: NifiNodeModel
}

export interface NifiNodeModel {
    replicaCount: number
}

export default Props;
