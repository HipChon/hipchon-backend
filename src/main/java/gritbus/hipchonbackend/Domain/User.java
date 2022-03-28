package gritbus.hipchonbackend.Domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import lombok.Getter;

@Entity
@Getter
public class User {
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	private String name;
	private String profileImage;
	private String email;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Post> postList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Myplace> myplaceList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<PostComment> postCommentList = new ArrayList<>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Mypost> mypostList = new ArrayList<>();

	public static void createKakaoUser(String token) throws RuntimeException {

		String reqURL = "https://kapi.kakao.com/v2/user/me";

		//access_token을 이용하여 사용자 정보 조회
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송


			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			JsonElement element = JsonParser.parseString(result);

			String id = element.getAsJsonObject().get("id").getAsString();
			boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
			String email = "";
			if(hasEmail){
				email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
			}


			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
