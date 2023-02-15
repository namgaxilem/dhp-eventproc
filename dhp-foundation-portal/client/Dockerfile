FROM nginx

WORKDIR /usr/share/nginx/html

RUN rm -rf ./*

COPY /build .
COPY /nginx.conf /etc/nginx/nginx.conf
COPY /set-env.sh .
COPY /build/index.html ./index.tpl.html

EXPOSE 80
# CMD ["nginx", "-g", "daemon off;"]
CMD ["sh", "-c", "cd /usr/share/nginx/html && ./set-env.sh && nginx -g 'daemon off;'"]
# ENTRYPOINT ["/bin/sh", "-c" , "npx react-inject-env set && echo 'hihi test' && npm start"]
