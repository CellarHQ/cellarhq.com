package com.cellarhq.repair.merger

import com.cellarhq.support.UserInputSupport
import com.cellarhq.support.userinput.OptionPromptType
import org.jooq.Field
import org.jooq.Record

trait Merger<T extends Record> extends UserInputSupport {

    boolean userInputFallback = true

    abstract Map<T, T> conflicts()
    abstract boolean merge(T source, T target)
    abstract List<T> detectSourceAndTarget(T a, T b)

    public Map<T, T> buildConflictMap(List<T> records, Field matchField) {
        Map<T, T> map = [:]

        records.each { T left ->
            if (map.containsKey(left) || map.containsValue(left)) {
                return
            }

            String leftValue = left.getValue(matchField).toString().toLowerCase()

            records.each { T right ->
                if (leftValue == right.getValue(matchField).toString().toLowerCase()) {
                    map[left] = right
                }
            }
        }

        return map
    }

    List<T> determineSourceAndTarget(T a, T b) {
        List<T> result = detectSourceAndTarget(a, b)
        if (result.empty) {
            result = userDeterminedSourceAndTarget(a, b)
        }
        return result
    }

    private List<T> userDeterminedSourceAndTarget(T a, T b) {
        if (userInputFallback) {
            println('## 1: #############################################')
            render(a)
            println('## 2: #############################################')
            render(b)
            println('###################################################')
            println('## WARN: Manual conflict resolution: Cannot determine which record to merge into')
            println('## INFO: Both records have been rendered above, select which number should be')
            println('##       merged into (the record remaining after the merge). This should be whatever')
            println('##       record seems like it has the most complete data.')
            println("##       If you're unsure, select '3' and the record will be skipped so the record")
            println('##       can be resolved manually later.')

            String selection = promptForInput(
                    new OptionPromptType(allowedValues: ['1': String, '2': String, '3': String]),
                    'Select record')

            if (selection == '1') {
                return [a, b]
            } else if (selection == '2') {
                return [b, a]
            }
        }

        return []
    }

    private void render(T record) {
        println(record.toString())
    }
}
