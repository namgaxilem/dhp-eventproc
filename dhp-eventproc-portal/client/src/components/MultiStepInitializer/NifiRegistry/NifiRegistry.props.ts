interface Props {
    isValidForm: (isValid: boolean, model?: NifiRegistryModel) => void
    model: NifiRegistryModel
}

export interface NifiRegistryModel {
    enabled: boolean
}

export default Props;
