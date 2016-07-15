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


FRONT-END:
1. Login Page                                                                                                   -added
    (1) autocomplete set to off for user/pass                                                                   -added
2. Registration Page                                                                                            -added
3. Home Page
    (1) Include user statics, navigation panel
3. Buy Page                                                                                                     -added
    (1) add "buy" tab, display json data from server via ajax
    (2) add search box, search posts
4. Add Post Page                                                                                                -added
5. Add "my posts" page


SECURITY FEATURES (protected against):
1. SQL Injection
2. Broken Authentication and Session Management
3. Cross-Site Scripting
4. Cross-Site Request Forgery
5. Clickjacking
6. Insecure connection (HTTP)
7. Password stored in clear text
8. Cacheable SSL Page 
9. Weak SSL Ciphers
