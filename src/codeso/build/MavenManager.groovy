package codeso.build

import org.jenkinsci.plugins.workflow.cps.DSL


class MavenManager {

    DSL pipelineSteps

    String mts = "mvn"

    MavenManager(DSL pipelineSteps) {
        this.pipelineSteps = pipelineSteps
    }

    void manage(bldParams) {
        pipelineSteps.git url: bldParams.vcs.scr,
                                branch: bldParams.vcs.revision,
                                credentialsId: ""
        String commitHash = pipelineSteps.sh script: 'git rev-parse HEAD', returnStdout: true
		iParams.bld.targets.each{ item ->
            mts = " " + mts + item
		}
        pipelineSteps.echo "$mts"
		pipelineSteps.stage("Maven Build") {
            pipelineSteps.container('$bldParams.tool-$bldParams.version') {
                sh "$mts"
            }
        }
    }
}
