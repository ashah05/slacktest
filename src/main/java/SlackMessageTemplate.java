import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.DividerBlock;
//import com.slack.api.model.block.composition.TextObject;
//import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.model.block.composition.MarkdownTextObject;
//import com.slack.api.model.block.composition.OptionObject;
//import com.slack.api.model.block.element.BlockElement;
//import com.slack.api.model.block.element.PlainTextInputElement;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

public class SlackMessageTemplate {

    public static List<LayoutBlock> buildTemplateBlocks(String domain, String tableRows, String athenzUiUrl) {
        List<LayoutBlock> blocks = new ArrayList<>();

        // Add header section
        SectionBlock headerSection = SectionBlock.builder()
                .text(MarkdownTextObject.builder()
                        .text("*Domain Group Membership Expiry Details*")
                        .build())
                .build();
        blocks.add(headerSection);

        // Add divider block
        DividerBlock divider = DividerBlock.builder().build();
        blocks.add(divider);

        // Add domain details section
        SectionBlock domainSection = SectionBlock.builder()
                .text(MarkdownTextObject.builder()
                        .text("You have one or more principals in your Athenz domain `" + domain + "` groups whose access will expire soon:")
                        .build())
                .build();
        blocks.add(domainSection);

        // Add divider block
        blocks.add(divider);

        // Add table section
        SectionBlock tableSection = SectionBlock.builder()
                .text(MarkdownTextObject.builder()
                        .text("```MEMBER           |   GROUP                       |   EXPIRATION\n"
                                + "-----------------------------------------------------------------------------\n"
                                + tableRows
                                + "```")
                        .build())
                .build();
        blocks.add(tableSection);

        // Add divider block
        blocks.add(divider);

        // Add review details section
        SectionBlock reviewSection = SectionBlock.builder()
                .text(MarkdownTextObject.builder()
                        .text("Please review this list and, if necessary, login to your Athenz UI to extend their expiration dates.")
                        .build())
                .build();
        blocks.add(reviewSection);

        return blocks;
    }
}
