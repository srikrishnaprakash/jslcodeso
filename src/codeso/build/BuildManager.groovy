package codeso.build

import org.jenkinsci.plugins.workflow.cps.DSL


class BuildManager {

    DSL pipelineSteps

    BuildManager(DSL pipelineSteps) {
        this.pipelineSteps = pipelineSteps
    }

    void execute(bldParams) {
        switch (bldParams.bld.tool) {
        case 'maven':
            MavenManager mvnMgr = new MavenManager(pipelineSteps)
            mvnMgr.manage(bldParams)
            break
        case 'gradle':
            GradleManager grdMgr = new GradleManager(pipelineSteps)
            grdMgr.manage(bldParams)
            break
        }
    }

}
