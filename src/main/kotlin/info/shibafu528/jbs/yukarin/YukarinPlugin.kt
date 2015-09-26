package info.shibafu528.jbs.yukarin

import info.shibafu528.kotlinbeam.registerCommand
import net.orekyuu.javatter.api.command.CommandManager
import net.orekyuu.javatter.api.plugin.OnPostInit
import net.orekyuu.javatter.api.service.TwitterUserService
import javax.inject.Inject

public class YukarinPlugin {

    @Inject lateinit private val commandManager: CommandManager
    @Inject lateinit private val twitterUserService: TwitterUserService

    @OnPostInit
    public fun initialize() {
        commandManager.registerCommand {
            command = "yukari"
            help = "Say ﾕｯｶﾘｰﾝ!!!!!"
            exec = {
                val selectedUser = twitterUserService.selectedAccount()
                selectedUser.ifPresent { user ->
                    user.createTweet()
                            .setAsync()
                            .setText("＼ﾕｯｶﾘｰﾝ／")
                            .tweet()
                }
            }
        }
    }
}
