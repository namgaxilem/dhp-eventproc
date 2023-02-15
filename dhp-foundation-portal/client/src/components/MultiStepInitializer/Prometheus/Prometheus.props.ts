interface Props {
    isValidForm: (isValid: boolean, model?: PrometheusModel) => void
    model: PrometheusModel
}

export interface PrometheusModel {
    enabled: boolean
}

export default Props;
