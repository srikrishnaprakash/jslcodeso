package codeso.parser
import org.jenkinsci.plugins.workflow.cps.DSL

class JSONRequestParser {

    Map stagesConfig
    DSL pipelineSteps
    Map stagesParam

    JSONRequestParser(DSL steps, Map params) {
        this.pipelineSteps = steps
        this.stagesParam = params
    }

    Map populateStagesDetail() {
        pipelineSteps.echo (stagesParam['name'])
        def stageM = [:]
        for (tob in stagesParam['stages'])  {
            iVal = [:]
            switch(tob['group']) {
                case "SCM":
                    iVal[tob['entity']] = getRepoDetails(tob)
                    stageM[tob['sname']] = iVal
                    break
                case "BUILD":
                    iVal[tob['entity']] = getBuildDetails(tob)
                    stageM[tob['sname']] = iVal
                    break
            }
        }
        return stageM
    }

    String getRepoDetails(Map scmStage) {
        def result = ""
        switch(scmStage['op']) {
            case "clone-gh":
                result = "git@github.com:" + scmStage['owner'] + "/" + scmStage['param'] + ".git"
                break
            case "clone-bb":
                result = "git@bitbucket.atlassian.com:" + scmStage['owner'] + "/" + scmStage['param'] + ".git"
                break
        }
        return result
    }

    String getBuildDetails(Map scmStage) {
        def result = ""
        switch(scmStage['op']) {
            case "package-mvn":
                result = "mvn package "
                break
            case "package-grad":
                result = "gradle build"
                break
        }
        return result
    }

}