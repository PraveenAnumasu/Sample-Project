package Build_MSTest.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.DotnetBuildStep
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

object Build_MSTest_DetailedLogging : BuildType({
    name = "detailed logging"

    vcs {
        root(_Self.vcsRoots.HttpsGithubComBurnashevaMstestDotnet2gitRefsHeadsMaster)
    }

    steps {
        dotnetBuild {
            projects = "PrimeService.Tests/PrimeService.Tests.csproj"
            args = "/t:vstest"
            logging = DotnetBuildStep.Verbosity.Detailed
            param("dotNetCoverage.dotCover.home.path", "%teamcity.tool.JetBrains.dotCover.CommandLineTools.2017.2.20171006.131508%")
        }
    }

    triggers {
        vcs {
        }
    }
})
