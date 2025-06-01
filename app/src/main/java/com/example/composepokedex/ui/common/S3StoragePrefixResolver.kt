package ph.theorangeplatform.camtime.ui.common

/*

class S3StoragePrefixResolver: AWSS3PluginPrefixResolver {
    override fun resolvePrefix(
        accessLevel: StorageAccessLevel,
        targetIdentity: String?,
        onSuccess: Consumer<String>,
        onError: Consumer<StorageException>?
    ) {
        when(accessLevel) {
            StorageAccessLevel.PUBLIC -> {
                onSuccess.accept("")
                return
            }
            StorageAccessLevel.PROTECTED -> {
                onSuccess.accept("$ID_PROTECTED_AWS$targetIdentity/")
                return
            }
            StorageAccessLevel.PRIVATE -> {
                onSuccess.accept("$ID_PRIVATE_AWS$targetIdentity/")
                return
            }
        }
    }

    companion object {
        private const val ID_PROTECTED_AWS = "protected/"
        private const val ID_PRIVATE_AWS = "private/"
    }
}*/
