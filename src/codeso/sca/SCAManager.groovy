package codeso.sca

import org.jenkinsci.plugins.workflow.cps.DSL

class SCAManager {

    DSL pipelineSteps

    SCAManager(DSL pipelineSteps) {
        this.pipelineSteps = pipelineSteps
    }
    void execute(scaParams) {
        switch (scaParams.sca.tool) {
        case 'sonar':
            SonarManager snrMgr = new SonarManager(pipelineSteps)
            snrMgr.scan(scaParams)
            break
        }
    }
}
