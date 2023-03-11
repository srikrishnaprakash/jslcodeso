package codeso

import org.jenkinsci.plugins.workflow.cps.DSL


class Manager {

    DSL pipelineSteps

    Manager(DSL pipelineSteps) {
        this.pipelineSteps = pipelineSteps
    }
    void process(stageMap){
        stageMap.each{key, value ->
            pipelineSteps.echo("Inside process:: $key:: $value")
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