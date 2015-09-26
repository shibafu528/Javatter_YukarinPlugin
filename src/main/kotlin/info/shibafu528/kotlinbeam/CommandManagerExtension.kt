package info.shibafu528.kotlinbeam

import net.orekyuu.javatter.api.command.Command
import net.orekyuu.javatter.api.command.CommandManager

/**
 * コマンド登録用のDSLを提供します。
 */
public class CommandBuilder(private val initializer: CommandBuilder.() -> Unit) {
    /** コマンド実行用の文字列 */
    var command: String = ""
    /** コマンドの説明 */
    var help: String = ""
    /** コマンドの処理 */
    var exec: (List<String>) -> Unit = {}

    init {
        initializer()
    }
}

/**
 * コマンドを登録します。
 */
public fun CommandManager.registerCommand(builder: CommandBuilder.() -> Unit) {
    val b = CommandBuilder(builder)
    registerCommand(object : Command {
        override fun command(): String = b.command

        override fun exec(args: List<String>) = b.exec(args)

        override fun help(): String = b.help
    })
}