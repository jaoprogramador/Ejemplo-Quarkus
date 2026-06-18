Para arrancar el proyecto necesitamos docker desctop y el contenedor Artemis MQ:

	docker run --detach --name mycontainer -p 61616:61616 -p 8161:8161 --rm apache/activemq-artemis:2.38.0-alpine
	
1.- Definiremos unas rutas en camel de mensajeria JMS de tal forma que si:

- el mensaje contiene en el cuerpo requestType a null lo enviamos a una cola de un tipo, 
- si tiene header.requestType=paymentDetails lo mandamos a jms:request.details.MailQueue
- si no es ninguna de las anteriores lo mandamos a jms:unrecognised.queue 

2.- Enterprise Integration Patterns - EIP) conocido como Recipient List (Lista de Destinatarios), combinado con arquitecturas desacopladas mediante AMQP/JMS con ActiveMQ Artemis.