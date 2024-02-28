package com.spbpu.util

import com.github.syari.kgit.KGit
import org.eclipse.jgit.errors.RepositoryNotFoundException
import java.io.File
import java.nio.file.FileAlreadyExistsException

const val JSL_SPEC_REPO_URL = "https://github.com/vpa-research/jsl-spec.git"
const val JSL_SPEC_REPO_PATH = "test-data/jsl-spec"
const val JSL_SPEC_DIR_PATH = "spec"

fun ensureJslSpecRepo() = ensureRepo(
    repoUrl = JSL_SPEC_REPO_URL,
    repoPath = JSL_SPEC_REPO_PATH
).resolve(JSL_SPEC_DIR_PATH)

fun ensureRepo(repoUrl: String, repoPath: String): File {
    val repoDir = File(repoPath)
    if (repoDir.isFile())
        throw FileAlreadyExistsException("Can't create directory; file $repoDir already exists.")
    if (!repoDir.exists())
        repoDir.mkdirs()
    val repo = try {
        KGit.open(repoDir)
    } catch (e: RepositoryNotFoundException) {
        KGit.cloneRepository {
            setURI(repoUrl)
            setDirectory(repoDir)
        }
    }
    repo.fetch()
    repo.pull()
    return repoDir.absoluteFile
}
