@Library('jslcodso')
import codeso.Manager
import codeso.parser.JSONRequestParser


pipeline {
    agent none
    
    stages {
        stage('Initializing') {
            steps {
                node('built-in') {
                    script {
                        def paramSpec = readJSON text: params.COPTIONS
                        def cjp = new JSONRequestParser(steps, paramSpec)
                        def res = cjp.populateStagesDetail()
                        println("Going for loop")
                        mgr = new Manager(steps)
                        for (stg in res.keySet()) {
                            stage(stg) {
                                i = 0
                                for (stgD in res[stg].keySet()) {
                                    println(stgD)
                                    println(stg[stgD])
                                    println(i)
                                    i++
                                    //mgr.process(stg)s
                                }
                                //res[stg].each{entry -> println "$entry.value"}
                            }
                        }
                    }
                }
            }
        }
    }
}
