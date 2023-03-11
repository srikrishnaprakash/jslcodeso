package codeso.build

import org.jenkinsci.plugins.workflow.cps.DSL


class BuildManager {

    DSL pipelineSteps

    BuildManager(DSL pipelineSteps) {
        this.pipelineSteps = pipelineSteps
    }

    void process(stageMap){
        stageMap.each{key, value ->
            switch (key) {
                case 'github':
                    pipelineSteps.git branch: 'main', credentialsId: 'GHID', url: "$value"
                    break
                case 'mvn':
                    pipelineSteps.sh "$value"
                    break
            }
        }
    }

}
