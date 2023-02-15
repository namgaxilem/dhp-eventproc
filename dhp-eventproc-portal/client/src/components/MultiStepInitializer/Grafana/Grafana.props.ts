interface Props {
    isValidForm: (isValid: boolean, model?: GrafanaModel) => void
    model: GrafanaModel
}

export interface GrafanaModel {
    enabled: boolean
}

export default Props;
