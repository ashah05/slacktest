import com.slack.api.Slack;
import java.io.IOException;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.List;

import com.slack.api.model.block.LayoutBlock;   

public class SlackMessageSender {

    public static void sendMessageToChannel(String accessToken, String channelId, String domain, String tableRows) {
       // String messageWithValues = messageTemplate.replace("{domain}", domain).replace("{tableRows}", tableRows);

        List<LayoutBlock> blocks = SlackMessageTemplate.buildTemplateBlocks(domain, tableRows, "YourAthenzUiUrl");

        Slack slack = Slack.getInstance();

        //List<LayoutBlock> blocks = messageWithValues.split("\\n\\n");

        // Build the API request to send the message to the channel
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .token(accessToken)
                .channel(channelId)
                .username("Athenz Notifications")
                .blocks(blocks)
                .build();

        try {
            // Make the API call to send the message
            ChatPostMessageResponse response = slack.methods().chatPostMessage(request);
            if (response.isOk()) {
                System.out.println("Message sent successfully to Slack channel.");
            } else {
                System.out.println("Failed to send message to Slack channel. Error: " + response.getError());
            }
        } catch (IOException | SlackApiException e) {
            System.err.println("Error sending message to Slack channel: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String accessToken = "###";
        String channelId = "###";
        String domain = "$$$";
        String tableRows = "$$$";
        sendMessageToChannel(accessToken, channelId, domain, tableRows);
    }
}