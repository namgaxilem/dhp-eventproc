interface Props {
    isValidForm: (isValid: boolean, model?: SecretsModel) => void
    model: SecretsModel
}

export interface SecretsModel {
    azureKeyVault: {
        keyvaultName: string
        userAssignedIdentityId: string
        tenantId: string
    }
    azureGitUsername: string
    azureGitPat: string
    azDevBasicToken: string
    certManagerRootCA: string
    certManagerRootCAPrivateKey: string
    truststorePassword: string
    nifiConfigurationEncryptionKey: string
    sensitivePropertiesEncryptionKey: string

    certManagerRootCAFileList: Array<any>
    certManagerRootCAPrivateKeyFileList: Array<any>
}

export default Props;