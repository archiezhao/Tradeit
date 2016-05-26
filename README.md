# Tradeit

This is an under-construction Java web application. The goal is to build a cragslist-like website.
Deployed on AWS with HTTPS enforced:
https://jianqizhao.com/Tradeit/
Function Milestones:

BACK-END:
1. User Authentication                                                                                          -added
    (1) Password Hashing                                                                                        -added  
    (2) implement authentication filter to check user in-session status (excluding login/registration page).    -added
2. Session Management                                                                                           -added  
    (1) implement login/logout session cookie management                                                        -added
    (2) set cookie secure, httponly attribute                   
3. Post Creation                                                                                                -added
    (1) Add Image upload                                                                                        -added
    (2) Add multiple image support
    (3) ensure image file secure
4. Post View                                                                                                    -added
    (1) add "view post" function                                                                                -added
    (2) get post information from server via json                                                               -added
5. Security (if not mentioned above)
    (1) page cache control                                                                                      -added
    (3) page x-frame-options


FRONT-END:
1. Login Page                                                                                                   -added
    (1) autocomplete set to off for user/pass                                                                   -added
2. Registration Page                                                                                            -added
3. Home Page                                                                                                    -added
    (1) add "view post" tab, display json data from server via ajax
4. Post Create Page                                                                                             -added
