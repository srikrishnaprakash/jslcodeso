package codeso.sca

import org.jenkinsci.plugins.workflow.cps.DSL


class SonarManager {

    DSL pipelineSteps


    SonarManager(DSL pipelineSteps) {
        this.pipelineSteps = pipelineSteps
    }

    void scan(scaParams) {
        pipelineSteps.container('$scaParams.tool-scanr') {
            pipelineSteps.sh('sonar-scanner -Dsonar.login=admin ' +
                                ' -Dsonar.password=Admin#2022' +
                                " -Dsonar.projectBaseDir=${scaParams.projectbasedir}" +
                                " -Dsonar.host.url=${scaParams.sonarhost}" +
                                " -Dsonar.projectKey=${scaParams.projectkey}" +
                                " -Dsonar.java.binaries=${scaParams.binarypath}")
        }
    }
}
