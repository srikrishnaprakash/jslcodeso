package codeso.build

import org.jenkinsci.plugins.workflow.cps.DSL


class GradleManager {

    DSL pipelineSteps

    GradleManager(DSL pipelineSteps) {
        this.pipelineSteps = pipelineSteps
    }

    void manage(bldParams) {
    }
}
