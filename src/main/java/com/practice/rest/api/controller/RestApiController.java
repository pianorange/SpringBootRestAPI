package com.practice.rest.api.controller;

import com.practice.rest.api.model.User;
import com.practice.rest.api.service.UserService;
import com.practice.rest.api.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value= "/user",method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers(){
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id){
        logger.info("Fetching User with id{}",id);
        User user = userService.findById(id);
        if(user == null){
            logger.error("User with id {} not found", id);
            return new ResponseEntity(new CustomErrorType("user with id" + id +"not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User : {}", user);

        if(userService.isUserExist(user)){
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name"
                                                            +user.getName()+"already exist"),HttpStatus.CONFLICT);
        }
        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }



//    @RestController : 우선, 우리는 Spring 4의 새로운 @RestController 어노테이션을 사용하고 있습니다.
//      이 주석은 각 메소드에 @ResponseBody를 사용하여 주석을 추가 할 필요가 없습니다.
//      후드 아래에서 @RestController는 @ResponseBody로 주석을 달고 @Controller와 @ResponseBody의 조합으로 간주 할 수 있습니다.
//
//    @RequestBody : 메소드 매개 변수에 @RequestBody가 주석 된 경우 Spring은 들어오는 HTTP 요청 본문
//      (해당 메소드의 @RequestMapping에 언급 된 URL의 경우)을 해당 매개 변수에 바인딩합니다.
//      이를 수행하는 동안 Spring은 HTTP 메시지 변환기 를 사용 하여 요청에있는 ACCEPT 또는 Content-Type 헤더를
//      기반으로 HTTP 요청 본문을 도메인 객체 [요청 본문을 도메인 객체로 역 직렬화 ] 로 변환합니다.
//
//    @ResponseBody : 메소드가 @ResponseBody로 주석 된 경우 Spring은 반환 값을 나가는 HTTP 응답 본문에 바인딩합니다.
//      이를 수행하는 동안 Spring은 HTTP 헤더에있는 Content-Type을 기반으로 HTTP 메시지 변환기 를 사용 하여 반환 값을
//      HTTP 응답 본문 [응답 본문으로 직렬화 ] 로 변환합니다. 이미 언급했듯이, Spring 4에서는이 주석의 사용을 중단 할 수 있습니다.
//
//    ResponseEntity 는 실제 거래입니다. 전체 HTTP 응답을 나타냅니다. 그것에 대해 좋은 점은 당신이 그것에 들어가는 모든 것을
//     통제 할 수 있다는 것입니다. 상태 코드, 헤더 및 본문을 지정할 수 있습니다. HTTP 응답에서 보내려는 정보를 전달하는 여러 생성자가 제공됩니다.
//
//    @PathVariable 이 어노테이션은 메소드 매개 변수가 URI 템플리트 변수 [ '{}'의 변수]에 바인드되어야 함을 나타냅니다.
//     기본적으로 @RestController, @RequestBody, ResponseEntity 및 @PathVariable은 Spring에서 REST API를
//     구현하는 데 필요한 모든 것입니다. 또한 spring은 사용자 정의 된 것을 구현하는 데 도움이되는 몇 가지 지원 클래스를 제공합니다.
//
//    MediaType : @RequestMapping 어노테이션을 사용하지 않았더라도 특정 컨트롤러 메소드 가 생성하거나 소비 할
//    MediaType을 지정하거나 ( 생성 또는 소비 속성 사용) 매핑을 더욱 좁힐 수 있습니다.













}
