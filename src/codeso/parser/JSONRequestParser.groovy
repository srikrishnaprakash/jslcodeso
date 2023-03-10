package codeso.parser
import org.jenkinsci.plugins.workflow.cps.DSL

class JSONRequestParser {

    Map stagesConfig
    DSL pipelineSteps
    Map stagesParam

    JsonParser(DSL steps, Map params) {
        this.pipelineSteps = steps
        this.stagesParam = params
    }

    Map populateStagesDetail() {
        pipelineSteps.echo (stagesParam['name'])
        def stageM = [:]
        for (tob in stagesParam['stages'])  {
            switch(tob['entity']) {
                case "SCM":
                    stageM[tob['sname']] = [ (tob['entity']): (getRepoDetails(tob)) ]
                    break
                case "BUILD":
                    stageM[tob['sname']] = [ (tob['entity']): (getBuildDetails(tob)) ]
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