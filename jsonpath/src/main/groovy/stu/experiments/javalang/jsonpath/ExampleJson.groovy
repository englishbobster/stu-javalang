package stu.experiments.javalang.jsonpath

import groovy.json.JsonBuilder
import groovy.json.JsonOutput

class ExampleJson {

    String buildJsonExample() {
        JsonBuilder builder = new JsonBuilder()
        builder {
            firstname 'Brian'
            secondname 'the Mixer'
            surname 'Damage'
            addresses(
                address {
                    number 42
                    street 'Builders rd'
                    city 'Divvers'
                    postcosd 'PE71LP'
                    country 'UK'
                },
                address {
                    number 1010
                    street 'Cresent av'
                    city 'Milwauki'
                    postcosd 'its a zip'
                    country 'Murrca'
                }
            )
            car {
                make 'Reliant Robin'
                regNr 'HFL 531L'
                year '2015'
            }
        }
        JsonOutput.prettyPrint(builder.toString())
    }

}
