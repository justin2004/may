build:
	docker build -t justin2004/may docker_image 

install: build
	docker run -v may-m2:/root/.m2 -v `pwd`:/mnt --rm -it justin2004/may bash -c 'lein pom && lein jar && lein install'

run-for-vim-iced: build install
	docker run --net=host -v may-m2:/root/.m2 -v `pwd`:/mnt --rm -it justin2004/may

