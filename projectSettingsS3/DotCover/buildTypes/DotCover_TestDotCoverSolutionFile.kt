package DotCover.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetRestore
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetTest

object DotCover_TestDotCoverSolutionFile : BuildType({
    name = "test + dotCover (solution file)"

    vcs {
        root(_Self.vcsRoots.HttpsGithubComBurnashevaMstestCoreAdapterGit)
    }

    steps {
        dotnetRestore {
            projects = "MSTestCore.sln"
            sources = "https://api.nuget.org/v3/index.json"
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%")
        }
        dotnetBuild {
            projects = "MSTestCore.sln"
        }
        dotnetTest {
            projects = "MSTestCore.sln"
            coverage = dotcover {
                toolPath = "%teamcity.tool.JetBrains.dotCover.CommandLineTools.DEFAULT%"
            }
            param("test.names", "ReturnFalse")
        }
    }
})
