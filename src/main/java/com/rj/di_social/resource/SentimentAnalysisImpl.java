package com.rj.di_social.resource;

import com.semantria.Session;
import com.semantria.mapping.Document;
import com.semantria.mapping.output.DocAnalyticData;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

/**
 * User: rjain
 * Date: 13/02/14
 * Time: 11:33 AM
 */

@Service
public class SentimentAnalysisImpl implements SentimentAnalysis {
    String key0 = "7cc62ee4-35a7-4646-8e30-f95206315afa";
    String secret0 = "3341c222-a8b3-426c-b5ad-69d6c3435ca6";

    String key1 = "ae7c82d5-1f25-46b3-9ab6-7b1bc1c77210";
    String secret1 = "62c026b7-c93b-47b0-960d-edad69f1e476";

    String key2 ="6a4ddc55-2408-4484-accb-eaccb82c3059";
    String secret2= "69412e4c-60fb-43c9-bce5-37bfa9e4a52b";

    String key3 = "a77bf07c-c5d3-4b20-8ec2-68d6fdbfca03";
    String secret3 = "67166d39-34ce-47fb-aaab-62db4d9a2003";

    String key4 = "87e156e3-fbb6-4309-9feb-55783b115f78";
    String secret4 = "25169ef3-9ce5-45ed-8dfe-0ad2e29e8b8a";

    String key5 = "594de5a8-682c-4aff-bd35-3dd8020f0b87";
    String secret5 = "0d0d493a-d938-4b7d-856a-ca8853a7dd26";

    String key6 = "7251fbaa-b863-42a7-a130-161dc23c0145";
    String secret6 = "4c401c57-f2bc-4c05-bae6-e24cbe1fcb03";

    String key7 = "90eb4e66-3ee8-4682-9b00-106bb5f64309";
    String secret7 = "a6a06d45-afbc-4212-86e4-72412084f270";

    String key8 = "a25a08fc-affa-47ea-b1a6-e27c2171b72f";
    String secret8 = "14b6bd11-3280-4d8c-90fe-78c18dcde3bf";

    String key9 = "97236e2e-9964-44dd-93a9-15557021f408";
    String secret9 = "b593e3a9-d806-4dc8-af31-f9db74279d99";

    static int keyCount= 0;
    @Override
    public float analyseSentiment(String text) {
        String keys[]= {key0,key1,key2,key3,key4,key5,key6,key7,key8,key9};
        String secrets[]= {secret0,secret1,secret2,secret3,secret4,secret5,secret6,secret7,secret8,secret9};

        String key= keys[keyCount];
        String secret= secrets[keyCount];

        float sentiment= 0;
        try {

            Session session = Session.createSession(key,secret, true);

            String uid = UUID.randomUUID().toString();

            session.queueDocument(new Document(uid, text));
            List<DocAnalyticData> docAnalyticDataList = session.getProcessedDocuments();
            if(!docAnalyticDataList.isEmpty())
                sentiment= docAnalyticDataList.get(0).getSentimentScore();

            String interval = "Week"; //getting the statistics for week's call of the session
            Long statistics = session.getStatistics(interval).getOverallCalls();
            if(statistics>= 15000){
                keyCount++;
                System.out.println("Key usage limit crossed, using next key: "+ keyCount);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

        return sentiment;
    }
}
