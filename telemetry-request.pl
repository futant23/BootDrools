#!C:\strawberry\perl\bin -w


#Telemetry limit request via STOMP
#this is assuming the STOMP transport connector is configured

use Net::Stomp;
use strict;
use Data::Dumper;


print "calling Stomp lib ..\n";

my $stomp = Net::Stomp->new({ hosts => [
           { hostname => 'localhost', port => 61613 },
           { hostname => 'localhost', port => 61613 },
         ] });

print "connecting to stomp ..\n";
$stomp->connect( {login=>'', passcode=> ''} );

print "subscribing to  /temp-queue/tqlimit \n";

$stomp->subscribe({
    destination   => '/temp-queue/tqlimit',
    'ack'         => 'auto',
    'activemq.prefetchSize' => 1,

});

my %head;
$head{'destination'}='/queue/telemetry';
$head{"reply-to"}='/temp-queue/tqlimit';

#create the telemetry xml
my $telemetry = <<TELEMETRY;
<telemetry sv="3" mnemonic="a" value="b" />

TELEMETRY


my $frame1 = Net::Stomp::Frame->new(
	  {  command => "SEND",
	     headers => \%head,
	     body=> $telemetry});

print "stomp->send_frame $frame1->body\n";
$stomp->send_frame($frame1);

my $frame = $stomp->receive_frame;
my $body = $frame->body;
print "$body\n";

$stomp->disconnect;


