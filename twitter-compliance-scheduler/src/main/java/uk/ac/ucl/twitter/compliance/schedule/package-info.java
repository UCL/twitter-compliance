/**
 * Scheduling of compliance tasks.
 * <p>
 * Tweets are checked for compliance submitting batch jobs on Twitter API.
 * The collection of tweets are grouped according to how often they should be
 * checked.
 * <p>
 * The supported frequencies are defined in the enum
 * {@code uk.ac.ucl.twitter.compliance.VerificationInterval}
 */
package uk.ac.ucl.twitter.compliance.schedule;
