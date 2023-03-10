package codeso.build

import org.jenkinsci.plugins.workflow.cps.DSL


class BuildManager {

    DSL pipelineSteps

    BuildManager(DSL pipelineSteps) {
        this.pipelineSteps = pipelineSteps
    }

    void execute(bldParams) {
        switch (bldParams.bld.tool) {
        case 'Maven':
            pipelineSteps.sh "mvn package" 
            break
        case 'Gradle':
            pipelineSteps.echo "Gradle under implementation..."
            break
        }
    }

}
